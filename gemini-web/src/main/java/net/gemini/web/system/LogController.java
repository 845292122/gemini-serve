package net.gemini.web.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.gemini.domain.system.log.LogDomainService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author edison
 */
@Tag(name = "日志模块")
@Validated
@RestController
@RequestMapping("system/log")
@RequiredArgsConstructor
public class LogController {

    private final LogDomainService logDomainService;
}
