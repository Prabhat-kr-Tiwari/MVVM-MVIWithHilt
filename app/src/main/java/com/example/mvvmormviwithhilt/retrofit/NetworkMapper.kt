package com.example.mvvmormviwithhilt.retrofit

import com.example.mvvmormviwithhilt.model.Blog
import com.example.mvvmormviwithhilt.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<BlogNetworkEntity, Blog> {
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {

        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )

    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    //converting all object into blog object
    fun mapFromEntityList(entities:List<BlogNetworkEntity>):List<Blog>{
        return entities.map { mapFromEntity(it) }
    }

}