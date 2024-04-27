package org.ettarak.api.services;

import lombok.RequiredArgsConstructor;
import org.ettarak.api.domains.HttpResponse;
import org.ettarak.api.domains.Note;
import org.ettarak.api.enums.Level;
import org.ettarak.api.exceptions.NoteNotFoundException;
import org.ettarak.api.repository.NoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.ettarak.api.utils.Utils.dateTimeFormatter;

@RequiredArgsConstructor
@Service
public class NoteService {
    // injection repository
    private  final NoteRepository noteRepository;
    // Method to get all notes from sever
    public HttpResponse<Note> getAllNotes() {
        return  HttpResponse.<Note>builder()
                .notes(noteRepository.findAll())
                .message(noteRepository.count() > 0 ? noteRepository.count() + " notes retrieved" : "No notes to display")
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .timeStamp(LocalDateTime.now().format(dateTimeFormatter()))
                .build();
    }

    // method to filter notes by Level
    public HttpResponse<Note> getNotesByLevel(Level level) {
        List<Note> notes = noteRepository.findNoteByLevel(level);
        return  HttpResponse.<Note>builder()
                .notes(notes)
                .message(notes.size() + " notes are in level "+ level + " importance" )
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .timeStamp(LocalDateTime.now().format(dateTimeFormatter()))
                .build();
    }

    // method to add new note
    public HttpResponse<Note> saveNote(Note note) {
        note.setCreateAt(LocalDateTime.now());
        return  HttpResponse.<Note>builder()
                .notes(Collections.singleton(noteRepository.save(note)))
                .message("Note created successfully")
                .statusCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .timeStamp(LocalDateTime.now().format(dateTimeFormatter()))
                .build();
    }

    // method to update note
    public HttpResponse<Note> updateNote(Note note) throws NoteNotFoundException {
        Optional<Note> optionalNote = Optional.of(noteRepository.findById(note.getId()))
                .orElseThrow(()-> new NoteNotFoundException("The note was not found on the server"));

        Note noteUpdated = optionalNote.get();
        noteUpdated.setId(note.getId());
        noteUpdated.setTitle(note.getTitle());
        noteUpdated.setDescription(note.getDescription());
        noteUpdated.setLevel(note.getLevel());
        noteRepository.save(noteUpdated);

        return HttpResponse.<Note>builder()
                .notes(Collections.singleton(noteUpdated))
                .message("Note updated successfully")
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .timeStamp(LocalDateTime.now().format(dateTimeFormatter()))
                .build();
    }
    // Method to delete note
    public  HttpResponse<Note> deleteNote(Long id) throws NoteNotFoundException {
        Optional<Note> optionalNote = Optional.of(noteRepository.findById(id))
                .orElseThrow(()-> new NoteNotFoundException("The note was not found on the server"));
        optionalNote.ifPresent(noteRepository::delete);
        return HttpResponse.<Note>builder()
                .notes(Collections.singleton(optionalNote.get()))
                .message("Note Deleted successfully")
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .timeStamp(LocalDateTime.now().format(dateTimeFormatter()))
                .build();
    }
}
