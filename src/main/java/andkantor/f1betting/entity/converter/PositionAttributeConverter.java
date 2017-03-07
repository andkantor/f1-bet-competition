package andkantor.f1betting.entity.converter;

import andkantor.f1betting.entity.Position;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PositionAttributeConverter implements AttributeConverter<Position, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Position position) {
        return position.getPosition();
    }

    @Override
    public Position convertToEntityAttribute(Integer position) {
        return new Position(position);
    }
}