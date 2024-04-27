package org.ettarak.api.resources;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ettarak.api.domains.HttpResponse;
import org.ettarak.api.domains.Note;
import org.ettarak.api.enums.Level;
import org.ettarak.api.exceptions.NoteNotFoundException;
import org.ettarak.api.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/note")
public class NoteResource {
    private final NoteService noteService;

    // get all notes
    @GetMapping(path = "/all")
    public ResponseEntity<HttpResponse<Note>>  getNotes() {
        return  ResponseEntity.ok().body(noteService.getAllNotes());
    }

    // add note
    @PostMapping(path = "/add")
    public ResponseEntity<HttpResponse<Note>>  saveNote(@RequestBody @Valid Note note) {
        return  ResponseEntity.created(
                URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/note/all").toUriString()))
                .body(noteService.saveNote(note));
    }

    // filter notes by Level
    @GetMapping(path = "/filter")
    public ResponseEntity<HttpResponse<Note>> filterNotes(@RequestParam(value = "level") Level level) {
        return  ResponseEntity.ok().body(noteService.getNotesByLevel(level));
    }

    // update note existing
    @PutMapping(path = "/update")
    public ResponseEntity<HttpResponse<Note>> updateNote(@RequestBody @Valid Note note) throws NoteNotFoundException {
        return ResponseEntity.ok().body(noteService.updateNote(note));
    }

    // delete note existing
    @DeleteMapping(path = "/delete/{noteId}")
    public  ResponseEntity<HttpResponse<Note>> deleteNote(@PathVariable(value = "noteId") Long id) throws NoteNotFoundException {
        return ResponseEntity.ok().body(noteService.deleteNote(id));
    }
}
