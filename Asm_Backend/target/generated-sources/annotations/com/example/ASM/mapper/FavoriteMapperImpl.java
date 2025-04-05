package com.example.ASM.mapper;

import com.example.ASM.dto.response.user.FavoriteResponse;
import com.example.ASM.entity.FavoriteProduct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class FavoriteMapperImpl implements FavoriteMapper {

    private final DatatypeFactory datatypeFactory;

    public FavoriteMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public FavoriteResponse toFavoriteResponse(FavoriteProduct favoriteProduct) {
        if ( favoriteProduct == null ) {
            return null;
        }

        FavoriteResponse.FavoriteResponseBuilder favoriteResponse = FavoriteResponse.builder();

        if ( favoriteProduct.getProduct() != null ) {
            favoriteResponse.product( String.valueOf( mapProductToString( favoriteProduct.getProduct() ) ) );
        }
        favoriteResponse.id( favoriteProduct.getId() );
        favoriteResponse.likedAt( xmlGregorianCalendarToString( dateToXmlGregorianCalendar( favoriteProduct.getLikedAt() ), null ) );

        return favoriteResponse.build();
    }

    private String xmlGregorianCalendarToString( XMLGregorianCalendar xcal, String dateFormat ) {
        if ( xcal == null ) {
            return null;
        }

        if (dateFormat == null ) {
            return xcal.toString();
        }
        else {
            Date d = xcal.toGregorianCalendar().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat( dateFormat );
            return sdf.format( d );
        }
    }

    private XMLGregorianCalendar dateToXmlGregorianCalendar( Date date ) {
        if ( date == null ) {
            return null;
        }

        GregorianCalendar c = new GregorianCalendar();
        c.setTime( date );
        return datatypeFactory.newXMLGregorianCalendar( c );
    }
}
