package com.maruchin.domaindrivenandroid.data.account

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.maruchin.domaindrivenandroid.data.units.Points
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val ACCOUNT_USERNAME = stringPreferencesKey("account_username")
val ACCOUNT_EMAIL = stringPreferencesKey("account_email")
val ACCOUNT_COLLECTED_POINTS = intPreferencesKey("account_collected_points")

class DefaultAccountStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AccountStorage {

    override fun getLoggedInAccount(): Flow<Account?> {
        return dataStore.data.map { preferences ->
            val username = preferences[ACCOUNT_USERNAME]
            val email = preferences[ACCOUNT_EMAIL]
            val collectedPoints = preferences[ACCOUNT_COLLECTED_POINTS]
            if (username != null && email != null && collectedPoints != null) {
                Account(
                    username = username,
                    email = email,
                    collectedPoints = Points(collectedPoints)
                )
            } else null
        }
    }

    override suspend fun saveLoggedInAccount(account: Account?) {
        dataStore.edit { preferences ->
            if (account == null) {
                preferences.remove(ACCOUNT_USERNAME)
                preferences.remove(ACCOUNT_EMAIL)
                preferences.remove(ACCOUNT_COLLECTED_POINTS)
            } else {
                preferences[ACCOUNT_USERNAME] = account.username
                preferences[ACCOUNT_EMAIL] = account.email
                preferences[ACCOUNT_COLLECTED_POINTS] = account.collectedPoints.value
            }
        }
    }
}
