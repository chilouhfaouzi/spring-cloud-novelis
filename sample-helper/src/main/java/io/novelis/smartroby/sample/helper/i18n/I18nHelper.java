package io.novelis.smartroby.sample.helper.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Configuration
public class I18nHelper {


    private static I18nHelper i18nHelper;

    private MessageSourceAccessor accessor;

    public static String getMessage(String key, Object... args) {
        return i18nHelper.accessor.getMessage(key, args, LocaleContextHolder.getLocale());
    }

    public static String getMessageDefault(String key, String defaultMessage, Object... args) {
        return i18nHelper.accessor.getMessage(key, args, defaultMessage, LocaleContextHolder.getLocale());
    }

    @Bean
    public AcceptHeaderLocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver() {
            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                return Locale.ENGLISH;
            }
        };
    }

    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setAlwaysUseMessageFormat(true);
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setCacheSeconds(5);
        messageSource.setBasenames("classpath:/lang/messages","classpath:/lang/documentation");

        return messageSource;
    }

    @PostConstruct
    protected void init() {
        accessor = new MessageSourceAccessor(messageSource());
        i18nHelper = this;
    }

}
