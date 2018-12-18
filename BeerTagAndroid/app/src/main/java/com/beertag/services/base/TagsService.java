package com.beertag.services.base;

import com.beertag.models.Tag;

import java.io.IOException;
import java.util.List;

public interface TagsService {
    Tag getTagById(int id) throws IOException;
    List<Tag> getAllTags() throws IOException;
    Tag addNewTag(Tag newTag) throws IOException;
    void deleteTag(int id) throws IOException;
}
