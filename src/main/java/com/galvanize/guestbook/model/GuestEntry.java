package com.galvanize.guestbook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GuestEntry {
    String name;
    String comment;
}
