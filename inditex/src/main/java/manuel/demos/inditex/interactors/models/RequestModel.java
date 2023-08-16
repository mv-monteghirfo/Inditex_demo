package manuel.demos.inditex.interactors.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
// TODO fluent setters?
public class RequestModel {

    private final int productId;
    private final int brandId;
    private final LocalDateTime applicationDate;
}
