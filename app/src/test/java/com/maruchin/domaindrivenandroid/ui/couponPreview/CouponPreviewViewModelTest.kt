package com.maruchin.domaindrivenandroid.ui.couponPreview

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCodesRepository
import com.maruchin.domaindrivenandroid.data.activationCode.FakeActivationCodesApi
import com.maruchin.domaindrivenandroid.data.activationCode.sampleActivationCode
import com.maruchin.domaindrivenandroid.data.activationCode.sampleActivationCodeJson
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.coupon.FakeCouponsApi
import com.maruchin.domaindrivenandroid.domain.coupon.CollectCouponUseCase
import com.maruchin.domaindrivenandroid.domain.coupon.GetCollectableCouponUseCase
import com.maruchin.domaindrivenandroid.domain.coupon.sampleCollectableCoupons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CouponPreviewViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)
    private val couponsApi = FakeCouponsApi()
    private val activationCodesApi = FakeActivationCodesApi()
    private val accountRepository = AccountRepository()
    private val couponsRepository = CouponsRepository(couponsApi, scope)
    private val activationCodesRepository = ActivationCodesRepository(activationCodesApi)
    private val getCollectableCouponUseCase =
        GetCollectableCouponUseCase(accountRepository, couponsRepository)
    private val collectCouponUseCase =
        CollectCouponUseCase(accountRepository, couponsRepository, activationCodesRepository, scope)
    private val viewModel by lazy {
        CouponPreviewViewModel(getCollectableCouponUseCase, collectCouponUseCase)
    }

    @Before
    fun before() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Emit selected coupon`() = scope.runTest {
        val collectableCoupon = sampleCollectableCoupons[1]

        viewModel.uiState.test {
            viewModel.selectCoupon(collectableCoupon.coupon.id)

            assertEquals(CouponPreviewUiState(), awaitItem())
            assertEquals(
                CouponPreviewUiState(
                    coupon = collectableCoupon,
                    isLoading = false,
                    isCollecting = false,
                    failedToLoadCoupon = false,
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `Collect coupon`() = scope.runTest {
        val collectableCoupon = sampleCollectableCoupons[1]
        val activationCode = sampleActivationCode
        activationCodesApi.activationCode = sampleActivationCodeJson

        viewModel.uiState.test {
            viewModel.selectCoupon(collectableCoupon.coupon.id)
            viewModel.collectCoupon()

            assertEquals(CouponPreviewUiState(), awaitItem())
            assertEquals(
                CouponPreviewUiState(
                    coupon = collectableCoupon,
                    isLoading = false,
                    isCollecting = true,
                    failedToLoadCoupon = false,
                ),
                awaitItem()
            )
            assertEquals(
                CouponPreviewUiState(
                    coupon = collectableCoupon,
                    isLoading = false,
                    isCollecting = false,
                    failedToLoadCoupon = false,
                ),
                awaitItem()
            )
            assertEquals(
                CouponPreviewUiState(
                    coupon = collectableCoupon.copy(
                        coupon = collectableCoupon.coupon.collect(activationCode),
                        canCollect = false,
                    ),
                    isLoading = false,
                    isCollecting = false,
                    failedToLoadCoupon = false,
                ),
                awaitItem()
            )
            advanceTimeBy(61_000)
            assertEquals(
                CouponPreviewUiState(
                    coupon = collectableCoupon.copy(
                        coupon = collectableCoupon.coupon,
                        canCollect = false,
                    ),
                    isLoading = false,
                    isCollecting = false,
                    failedToLoadCoupon = false,
                ),
                expectMostRecentItem()
            )
        }
    }
}
