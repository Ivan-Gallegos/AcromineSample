package com.example.acrominesample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.acrominesample.network.AcromineApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.InputStreamReader
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class MainActivityViewModelTest {

    private var server: MockWebServer? = null

    private var viewModel: MainActivityViewModel = MainActivityViewModel()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    var mockResponseBody: String? = null

    @Before
    fun setUp() {
        server = MockWebServer()
        try {
            val isReader = this.javaClass.classLoader?.getResourceAsStream("acronym.json")
            val reader = InputStreamReader(isReader)
            mockResponseBody = reader.readText()
            reader.close()
        } catch (ex: Exception) {
            println(ex.localizedMessage)
        }
    }

    @Test
    fun successfulResponseTest() {
        startServer(200)
        val latch = CountDownLatch(1)
        val url = server?.url("/").toString()
        AcromineApi.BASE_URL = url
        viewModel.callSf("usaf")
        viewModel.list.observeForever {
            assertNotNull(it)
            latch.countDown()
        }
        latch.await(20000, TimeUnit.MILLISECONDS)
    }

    private fun startServer(responseCode: Int) {
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(responseCode)
        mockResponseBody?.let { mockedResponse.setBody(it) }
        server?.enqueue(mockedResponse)
        server?.start()
    }
}