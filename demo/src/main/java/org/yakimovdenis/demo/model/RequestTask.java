package org.yakimovdenis.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.yakimovdenis.demo.RequestTaskDeserializer;

@JsonDeserialize(using = RequestTaskDeserializer.class)
public interface RequestTask {
}
