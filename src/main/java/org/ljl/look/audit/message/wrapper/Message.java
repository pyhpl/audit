package org.ljl.look.audit.message.wrapper;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message<T> {

    private MessageMethod method;
    private T body;

    public enum  MessageMethod {
        GET, PUT, DELETE, POST
    }
}
