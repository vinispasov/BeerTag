package com.beertag.services;

import com.beertag.models.Tag;
import com.beertag.repositories.base.TagsRepository;
import com.beertag.services.base.TagsService;

import java.io.IOException;
import java.util.List;

public class HttpTagsService implements TagsService {
    private final TagsRepository mTagsRepository;

    public HttpTagsService(TagsRepository tagsRepository) {
        mTagsRepository = tagsRepository;
    }

    @Override
    public Tag getTagById(int tagId) throws IOException {
        return mTagsRepository.getTagById(tagId);
    }

    @Override
    public List<Tag> getAllTags() throws IOException {
        return mTagsRepository.getAllTags();
    }

    @Override
    public Tag addNewTag(Tag newTag) throws IOException {
        return mTagsRepository.addNewTag(newTag);
    }

    @Override
    public void deleteTag(int tagId) throws IOException {
        mTagsRepository.deleteTag(tagId);
    }
}
