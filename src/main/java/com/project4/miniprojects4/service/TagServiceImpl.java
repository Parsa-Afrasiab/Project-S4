package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.Tag;
import com.project4.miniprojects4.repository.TagRepository;
import org.springframework.stereotype.Service;
import com.project4.miniprojects4.model.Blog;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    @Transactional
    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tag not found: " + id));

        for (Blog blog : tag.getBlogs()) {
            blog.getTags().removeIf(
                    t -> t.getId().equals(id)
            );
        }

        tagRepository.delete(tag);
    }
}
