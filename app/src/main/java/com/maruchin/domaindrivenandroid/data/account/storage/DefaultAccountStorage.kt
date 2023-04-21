package com.maruchin.domaindrivenandroid.data.account.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.maruchin.domaindrivenandroid.data.account.Account
import com.maruchin.domaindrivenandroid.data.values.Email
import com.maruchin.domaindrivenandroid.data.values.Points
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val ACCOUNT_EMAIL = stringPreferencesKey("account_email")
val ACCOUNT_COLLECTED_POINTS = intPreferencesKey("account_collected_points")

class DefaultAccountStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AccountStorage {

    override fun getLoggedInAccount(): Flow<Account?> {
        return dataStore.data.map { preferences ->
            val email = preferences[ACCOUNT_EMAIL]
            val collectedPoints = preferences[ACCOUNT_COLLECTED_POINTS]
            if (email != null && collectedPoints != null) {
                Account(
                    email = Email(email),
                    collectedPoints = Points(collectedPoints)
                )
            } else null
        }
    }

    override suspend fun saveLoggedInAccount(account: Account?) {
        dataStore.edit { preferences ->
            if (account == null) {
                preferences.remove(ACCOUNT_EMAIL)
                preferences.remove(ACCOUNT_COLLECTED_POINTS)
            } else {
                preferences[ACCOUNT_EMAIL] = account.email.value
                preferences[ACCOUNT_COLLECTED_POINTS] = account.collectedPoints.value
            }
        }
    }
}
