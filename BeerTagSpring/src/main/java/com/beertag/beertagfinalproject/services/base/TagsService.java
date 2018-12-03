package com.beertag.beertagfinalproject.services.base;

import com.beertag.beertagfinalproject.models.Tag;

import java.util.List;

public interface TagsService {
    Tag getTagById(int id);
    List<Tag> getAllTags();
    Tag addNewTag(Tag newTag);
    void deleteTag(int id);
}
