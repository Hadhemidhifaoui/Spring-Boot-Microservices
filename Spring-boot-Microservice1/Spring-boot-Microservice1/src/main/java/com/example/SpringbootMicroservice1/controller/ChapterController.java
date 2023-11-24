package com.example.SpringbootMicroservice1.controller;

import com.example.SpringbootMicroservice1.model.Chapter;
import com.example.SpringbootMicroservice1.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapters")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @PutMapping("/{chapterId}")
    public ResponseEntity<?> updateChapter(@PathVariable Long chapterId, @RequestBody Chapter updatedChapter) {
        Chapter chapter = chapterService.updateChapter(chapterId, updatedChapter);

        if (chapter != null) {
            return ResponseEntity.ok(chapter);
        } else {
            return new ResponseEntity<>("Chapter not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{chapterId}")
    public ResponseEntity<?> getChapterById(@PathVariable Long chapterId) {
        Chapter chapter = chapterService.findChapterById(chapterId);

        if (chapter != null) {
            return ResponseEntity.ok(chapter);
        } else {
            return new ResponseEntity<>("Chapter not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Chapter>> getAllChapters() {
        List<Chapter> chapters = chapterService.getAllChapters();
        return ResponseEntity.ok(chapters);
    }

    @PostMapping
    public ResponseEntity<Chapter> saveChapter(@RequestBody Chapter chapter) {
        Chapter savedChapter = chapterService.saveChapter(chapter);
        return new ResponseEntity<>(savedChapter, HttpStatus.CREATED);
    }

    @DeleteMapping("/{chapterId}")
    public ResponseEntity<?> deleteChapter(@PathVariable Long chapterId) {
        chapterService.deleteChapter(chapterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

