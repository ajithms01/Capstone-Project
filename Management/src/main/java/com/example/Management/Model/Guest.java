package com.example.Management.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Guest {
    private Long guestId;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private Long eventId;
}
