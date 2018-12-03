package com.beertag.beertagfinalproject.controllers;

import com.beertag.beertagfinalproject.models.Tag;
import com.beertag.beertagfinalproject.services.base.TagsService;
import com.beertag.beertagfinalproject.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.TAGS_ROOT_MAPPING)
public class TagsApiController {

    private final TagsService tagsService;

    @Autowired
    public TagsApiController(TagsService tagsService){
        this.tagsService=tagsService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Tag getTagById(@PathVariable int id) {
        return tagsService.getTagById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Tag> getAllTags() {
        return tagsService.getAllTags();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Tag addNewTag(@RequestBody @Valid Tag newTag) {
        return tagsService.addNewTag(newTag);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTag(@PathVariable(value = "id") @Valid int id) {
        tagsService.deleteTag(id);
    }

}
