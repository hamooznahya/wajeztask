package com.example.wajeztask.domain.mappers

import com.example.wajeztask.data.dto.ElixirResponseList
import com.example.wajeztask.data.dto.ElixirsResponse
import com.example.wajeztask.data.dto.WizardsResponse
import com.example.wajeztask.domain.model.ElixirList
import com.example.wajeztask.domain.model.Elixirs
import com.example.wajeztask.domain.model.Ingredient
import com.example.wajeztask.domain.model.Inventor
import com.example.wajeztask.domain.model.Wizards

object WizardsMapper {

    fun map(wizardsResponse: WizardsResponse): Wizards {
        return Wizards(
            id = wizardsResponse.id,
            firstName = wizardsResponse.firstName,
            lastName = wizardsResponse.lastName,
            elixirs = wizardsResponse.elixirs.map { map(it) }
        )
    }

    fun mapElixirs(elixirsResponse: ElixirsResponse): Elixirs {
        return Elixirs(
            id = elixirsResponse.id,
            name = elixirsResponse.name,
            effect = elixirsResponse.effect,
            sideEffects = elixirsResponse.sideEffects,
            characteristics = elixirsResponse.characteristics,
            time = elixirsResponse.time,
            difficulty = elixirsResponse.difficulty,
            manufacturer = elixirsResponse.manufacturer,
            ingredients = elixirsResponse.ingredients.map { ingredientResponse ->
                Ingredient(
                    id = ingredientResponse.id,
                    name = ingredientResponse.name
                )
            },
            inventors = elixirsResponse.inventors.map { inventorResponse ->
                Inventor(
                    id = inventorResponse.id,
                    firstName = inventorResponse.firstName,
                    lastName = inventorResponse.lastName
                )
            }
        )
    }


    fun mapList(wizardsResponseList: List<WizardsResponse>): List<Wizards> {
        return wizardsResponseList.map { map(it) }
    }

    private fun map(elixirResponse: ElixirResponseList): ElixirList {
        return ElixirList(
            id = elixirResponse.id,
            name = elixirResponse.name
        )
    }

}