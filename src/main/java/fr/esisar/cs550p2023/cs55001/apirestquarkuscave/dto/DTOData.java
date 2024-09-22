package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public abstract class DTOData {

	public Long id;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime measureDate;

}
