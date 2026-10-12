package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.Tag;
import java.util.List;

public interface TagService {

    void save(Tag tag);

    List<Tag> getAllTags();

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    void deleteTag(Long id);
}
