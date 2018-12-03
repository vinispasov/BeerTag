package com.beertag.beertagfinalproject.services;

import com.beertag.beertagfinalproject.models.Tag;
import com.beertag.beertagfinalproject.repositories.base.TagsRepository;
import com.beertag.beertagfinalproject.services.base.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {
    private final TagsRepository tagsRepository;

    @Autowired
    public TagsServiceImpl(TagsRepository tagsRepository){
        this.tagsRepository=tagsRepository;
    }

    @Override
    public Tag getTagById(int id) {
        return tagsRepository.getTagById(id);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagsRepository.getAllTags();
    }

    @Override
    public Tag addNewTag(Tag newTag) {
        return tagsRepository.addNewTag(newTag);
    }

    @Override
    public void deleteTag(int id) {
        tagsRepository.deleteTag(id);
    }
}
