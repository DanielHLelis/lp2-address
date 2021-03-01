package br.cefetmg.address.model.serializer;

import br.cefetmg.address.model.domain.Model;

import java.util.List;

public interface Serializer<T extends Model> {
    String serialize(T p);

    String serialize(List<T> ps);

    List<T> deserialize(String sd);
}
