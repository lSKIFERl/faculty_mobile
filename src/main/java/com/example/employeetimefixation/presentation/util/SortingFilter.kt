package com.example.employeetimefixation.presentation.util

import com.example.employeetimefixation.presentation.model.TableItemModel

class SortingFilter(var originList: List<TableItemModel>) {
    private val mapOfSortStates: MutableMap<SortType, Int> = emptyMap<SortType, Int>().toMutableMap()

    fun addSortState(sortType: SortType): Int {
        var count = 1
        if (mapOfSortStates.containsKey(sortType))
            mapOfSortStates.apply {
                count += getOrDefault(sortType, 1)
                replace(sortType, count)
                if (count == 3) remove(sortType)
            } else {
                mapOfSortStates.put(sortType, 1)
        }
        return count
    }

    fun sort(): List<TableItemModel> {
        for (sortType in mapOfSortStates.keys)
            originList = originList.sortCase(sortType)
        return originList
    }

    private fun List<TableItemModel>.sortCase(sortType: SortType): List<TableItemModel> {
        val count = mapOfSortStates.get(sortType)
        if(count == 3) return this
        val list = when(sortType) {
            SortType.NUMBER -> sortedWith(compareBy{it.number})
            SortType.NAME -> sortedWith(compareBy{it.name})
            SortType.POSITION -> sortedWith(compareBy{it.position})
            SortType.TIME_FROM -> sortedWith(compareBy{it.timeFrom})
            SortType.TIME_TO -> sortedWith(compareBy{it.timeTo})
            SortType.BRAKE -> sortedWith(compareBy{it.brake})
        }
        return if (count == 1) list else list.asReversed()
    }
}
