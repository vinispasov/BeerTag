package com.beertag.beertagfinalproject.utils.mappers;

import com.beertag.beertagfinalproject.models.Tag;
import com.beertag.beertagfinalproject.models.dto_models.BeerDTO;
import com.beertag.beertagfinalproject.models.dto_models.TagDTO;
import com.beertag.beertagfinalproject.utils.mappers.base.BeersMapper;
import com.beertag.beertagfinalproject.utils.mappers.base.TagsMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TagsMapperImpl implements TagsMapper {

    @Override
    public TagDTO mapTagToDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();

        if (!Objects.equals(tagDTO, null)) {
           // BeersMapper mapper=new BeersMapperImpl();
           // List<BeerDTO> beerDTOS= tag.getBeers().stream().map(beer -> mapper.mapBeerToDTO(beer)).collect(Collectors.toList());

            tagDTO = new TagDTO(
                    tag.getTagId(),
                    tag.getTag(),
                    tag.getBeers());
        }
        return tagDTO;
    }
}
