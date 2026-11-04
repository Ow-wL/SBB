package com.mysite.sbb.book.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @NotEmpty(message = "도서 제목은 필수 항목입니다.")
    private String subject;

    @NotEmpty(message = "저자는 필수 항목입니다.")
    @Size(min = 2, max = 30, message = "저자는 2자 이상 30자 이하로 입력하세요.")
    private String author;

    @NotEmpty(message = "평점은 필수 항목입니다.")
    private String rating;

    @NotEmpty(message = "감상평은 필수 항목입니다.")
    @Size(min = 10, message = "감상평은 10자 이상 입력하세요.")
    private String content;
}
