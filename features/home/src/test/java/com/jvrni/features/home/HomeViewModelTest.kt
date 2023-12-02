package com.jvrni.features.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import com.jvrni.core.domain.GetUsers
import com.jvrni.core.domain.models.Location
import com.jvrni.core.domain.models.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    private val getUsers = mockk<GetUsers>()

    private val viewModel by lazy { spyk(HomeViewModel(getUsers)) }

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `GIVEN a fake information WHEN getting it THEN return success value`() = runTest {
        coEvery { getUsers.invoke() } returns flowOf(PagingData.from(list))

        assertEquals(
            viewModel.state.value.data.first().collectDataForTest().first().name,
            list[0].name
        )
    }

    @Test
    fun `GIVEN a fake information WHEN getting it THEN return failure value`() = runTest {
        coEvery { getUsers.invoke() } returns flowOf(PagingData.empty())

        assertEquals(
            viewModel.state.value.data.first().collectDataForTest(),
            emptyList<User>()
        )
    }

    companion object {
        val list = listOf(
            User(
                name = "João",
                lastName = "Varani",
                email = "joao.varani@vlue.com",
                picture = "",
                phone = "",
                registeredDate = "",
                location = Location(
                    number = 0,
                    street = "",
                    city = "",
                    state = "",
                    country = ""
                )
            ),
            User(
                name = "Guillermo",
                lastName = "Lopez",
                email = "guillermo@vlue.com",
                picture = "",
                phone = "",
                registeredDate = "",
                location = Location(
                    number = 0,
                    street = "",
                    city = "",
                    state = "",
                    country = ""
                )
            ),
        )
    }
}

private suspend fun <T : Any> PagingData<T>.collectDataForTest(): List<T> {
    val dcb = object : DifferCallback {
        override fun onChanged(position: Int, count: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }
    val items = mutableListOf<T>()
    val dif = object : PagingDataDiffer<T>(dcb, StandardTestDispatcher()) {
        override suspend fun presentNewList(
            previousList: NullPaddedList<T>,
            newList: NullPaddedList<T>,
            lastAccessedIndex: Int,
            onListPresentable: () -> Unit
        ): Int? {
            for (idx in 0 until newList.size)
                items.add(newList.getFromStorage(idx))
            onListPresentable()
            return null
        }
    }
    dif.collectFrom(this)
    return items
}