package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class AuthRequestDTO {



//  "username": "string",
//  "password": "qvwS-%+BV@_\\CtTTfZ /:5p5Tz3(I+?j>@9w[O?'Rt`(:B:*4STL7$purqEbrg(YXCeHM;K^%M@~"
    String username;
    String password;
}
