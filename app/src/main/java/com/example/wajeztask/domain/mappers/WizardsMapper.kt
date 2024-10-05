package com.example.wajeztask.domain.mappers

import com.example.wajeztask.data.dto.ElixirResponseList
import com.example.wajeztask.data.dto.ElixirsResponse
import com.example.wajeztask.data.dto.WizardsResponse
import com.example.wajeztask.data.entities.ElixirListEntity
import com.example.wajeztask.domain.model.Elixirs
import com.example.wajeztask.domain.model.Ingredient
import com.example.wajeztask.domain.model.Inventor
import com.example.wajeztask.data.entities.WizardsEntity
import com.example.wajeztask.domain.model.ElixirList
import com.example.wajeztask.domain.model.Wizard

object WizardsMapper {

    fun mapWizardsEntity(wizardsResponse: WizardsResponse): WizardsEntity {
        return WizardsEntity(
            id = wizardsResponse.id,
            firstName = wizardsResponse.firstName,
            lastName = wizardsResponse.lastName,
            elixirs = wizardsResponse.elixirs.map { mapElixirListEntity(it) }
        )
    }

    fun mapWizardsEntityToModel(entity: WizardsEntity): Wizard{
        return Wizard(
            id = entity.id,
            firstName = entity.firstName,
            lastName = entity.lastName,
            elixirs = entity.elixirs.map { mapElixirList(it) }
        )
    }

    private fun mapElixirList(elixirResponse: ElixirListEntity): ElixirList {
        return ElixirList(
            id = elixirResponse.id,
            name = elixirResponse.name
        )
    }

    fun mapWizardDetails(wizardsResponse: WizardsResponse): Wizard {
        return Wizard(
            id = wizardsResponse.id,
            firstName = wizardsResponse.firstName,
            lastName = wizardsResponse.lastName,
            elixirs = wizardsResponse.elixirs.map { mapElixirList(it) }
        )
    }

    fun mapElixirsDetails(elixirsResponse: ElixirsResponse): Elixirs {
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


    fun mapList(wizardsResponseList: List<WizardsResponse>): List<WizardsEntity> {
        return wizardsResponseList.map { mapWizardsEntity(it) }
    }

    private fun mapElixirListEntity(elixirResponse: ElixirResponseList): ElixirListEntity {
        return ElixirListEntity(
            id = elixirResponse.id,
            name = elixirResponse.name
        )
    }

    private fun mapElixirList(elixirResponse: ElixirResponseList): ElixirList {
        return ElixirList(
            id = elixirResponse.id,
            name = elixirResponse.name
        )
    }

}