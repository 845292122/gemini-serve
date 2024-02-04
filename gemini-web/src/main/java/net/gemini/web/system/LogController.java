package net.gemini.web.system;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import net.gemini.domain.system.log.LogDomainService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author edison
 */
@Api(tags = "日志模块")
@Validated
@RestController
@RequestMapping("system/log")
@RequiredArgsConstructor
public class LogController {

    private final LogDomainService logDomainService;
}
