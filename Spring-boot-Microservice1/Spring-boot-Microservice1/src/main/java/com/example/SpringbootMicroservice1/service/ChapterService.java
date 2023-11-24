package com.example.SpringbootMicroservice1.service;

import com.example.SpringbootMicroservice1.model.Chapter;
import com.example.SpringbootMicroservice1.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    public Chapter updateChapter(Long chapterId, Chapter updatedChapter) {
        Optional<Chapter> existingChapter = chapterRepository.findById(chapterId);

        if (existingChapter.isPresent()) {
            Chapter chapter = existingChapter.get();
            chapter.setName(updatedChapter.getName());
            chapter.setDescription(updatedChapter.getDescription());
            // Autres propriétés à mettre à jour si nécessaire

            return chapterRepository.save(chapter);
        }

        return null;
    }

    public Chapter findChapterById(Long chapterId) {
        Optional<Chapter> chapter = chapterRepository.findById(chapterId);
        return chapter.orElse(null);
    }

    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    public Chapter saveChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public void deleteChapter(Long chapterId) {
        chapterRepository.deleteById(chapterId);
    }

    // Ajoutez d'autres méthodes de service au besoin
}

