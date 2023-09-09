package br.edu.ifsp.spo.ctpacolhe.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvatarUpdateDto {
	@NotEmpty
	@Schema(example = "https://media.discordapp.net/attachments/1077345452694970438/1107082557515890758/Mask_group_9.png?width=468&height=468")
	private String urlAvatar;
}
