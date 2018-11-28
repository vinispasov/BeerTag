package com.beertag.beertagfinalproject.utils.mappers.base;

import com.beertag.beertagfinalproject.models.Tag;
import com.beertag.beertagfinalproject.models.dto_models.TagDTO;

public interface TagsMapper {
    TagDTO mapTagToDTO(Tag tag);
}
