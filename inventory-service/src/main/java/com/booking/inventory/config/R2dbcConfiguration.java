package com.booking.inventory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableR2dbcAuditing
@EnableScheduling
public class R2dbcConfiguration {

}
