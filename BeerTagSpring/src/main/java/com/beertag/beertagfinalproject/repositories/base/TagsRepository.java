package com.beertag.beertagfinalproject.repositories.base;

import com.beertag.beertagfinalproject.models.Tag;

import java.util.List;

public interface TagsRepository {
    Tag getTagById(int id);
    List<Tag> getAllTags();
    Tag addNewTag(Tag newTag);
    void deleteTag(int id);
}
