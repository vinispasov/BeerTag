package com.beertag.repositories.base;

import com.beertag.models.Tag;

import java.io.IOException;
import java.util.List;

public interface TagsRepository {
    Tag getTagById(int id) throws IOException;
    List<Tag> getAllTags() throws IOException;
    Tag addNewTag(Tag newTag) throws IOException;
    void deleteTag(int id) throws IOException;
}
