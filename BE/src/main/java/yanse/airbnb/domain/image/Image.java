package yanse.airbnb.domain.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import yanse.airbnb.type.ImageType;

@Entity
public class Image {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private Long id;

	private String url;

	private ImageType imageType;

	private String title;

	private String content;

}