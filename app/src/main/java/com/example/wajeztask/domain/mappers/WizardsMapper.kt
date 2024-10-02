package com.example.wajeztask.domain.mappers

import com.example.wajeztask.data.dto.ElixirResponse
import com.example.wajeztask.data.dto.WizardsResponse
import com.example.wajeztask.domain.model.Elixir
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

    fun mapList(wizardsResponseList: List<WizardsResponse>): List<Wizards> {
        return wizardsResponseList.map { map(it) }
    }

    private fun map(elixirResponse: ElixirResponse): Elixir {
        return Elixir(
            id = elixirResponse.id,
            name = elixirResponse.name
        )
    }

}