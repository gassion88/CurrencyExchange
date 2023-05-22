package com.gassion.currencyexchange.entities.DTO.DTOFactories;

import com.gassion.currencyexchange.entities.DTO.DTO;
import com.gassion.currencyexchange.entities.Model;

public abstract class DTOFactory {
    public abstract<M extends Model> DTO getFromModel(M model);
}
