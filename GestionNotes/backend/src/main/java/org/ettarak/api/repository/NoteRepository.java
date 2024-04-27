package org.ettarak.api.repository;

import org.ettarak.api.domains.Note;
import org.ettarak.api.enums.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findNoteByLevel(Level level);
    void deleteNoteById(Long id);
}
