package com.galvanize.guestbook.controller;

import com.galvanize.guestbook.model.GuestEntry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guestbook")
public class GuestController {
    List<GuestEntry> guestEntryList;

    public GuestController(List<GuestEntry> guestEntryList) {
        this.guestEntryList = guestEntryList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntry(@RequestBody GuestEntry guestEntry) {
        guestEntryList.add(guestEntry);
    }

    @GetMapping
    public List<GuestEntry> getAllEntries() {
        return guestEntryList;
    }

}
