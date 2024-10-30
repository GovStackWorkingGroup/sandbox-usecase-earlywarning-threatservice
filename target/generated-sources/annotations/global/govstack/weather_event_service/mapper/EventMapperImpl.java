package global.govstack.weather_event_service.mapper;

import global.govstack.weather_event_service.models.EventFullDto;
import global.govstack.weather_event_service.repository.entity.Event;
import global.govstack.weather_event_service.repository.entity.EventStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-17T13:49:16+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventFullDto entityToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        EventStatus status = null;
        String country = null;
        String county = null;
        LocalDateTime periodStart = null;
        LocalDateTime periodEnd = null;
        String englishMsg = null;
        String swahiliMsg = null;
        String amharicMsg = null;
        String arabicMsg = null;
        String somaliMsg = null;
        String frenchMsg = null;
        String oromoMsg = null;
        String tigrinyaMsg = null;
        String lugandaMsg = null;
        String kinyarwandaMsg = null;
        String kirundiMsg = null;
        String karamojongMsg = null;
        String pokotMsg = null;
        String nuerAndDinkaMsg = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        UUID updatedBy = null;

        id = event.getId();
        title = event.getTitle();
        status = event.getStatus();
        country = event.getCountry();
        county = event.getCounty();
        periodStart = event.getPeriodStart();
        periodEnd = event.getPeriodEnd();
        englishMsg = event.getEnglishMsg();
        swahiliMsg = event.getSwahiliMsg();
        amharicMsg = event.getAmharicMsg();
        arabicMsg = event.getArabicMsg();
        somaliMsg = event.getSomaliMsg();
        frenchMsg = event.getFrenchMsg();
        oromoMsg = event.getOromoMsg();
        tigrinyaMsg = event.getTigrinyaMsg();
        lugandaMsg = event.getLugandaMsg();
        kinyarwandaMsg = event.getKinyarwandaMsg();
        kirundiMsg = event.getKirundiMsg();
        karamojongMsg = event.getKaramojongMsg();
        pokotMsg = event.getPokotMsg();
        nuerAndDinkaMsg = event.getNuerAndDinkaMsg();
        createdAt = event.getCreatedAt();
        updatedAt = event.getUpdatedAt();
        updatedBy = event.getUpdatedBy();

        EventFullDto eventFullDto = new EventFullDto( id, title, status, country, county, periodStart, periodEnd, englishMsg, swahiliMsg, amharicMsg, arabicMsg, somaliMsg, frenchMsg, oromoMsg, tigrinyaMsg, lugandaMsg, kinyarwandaMsg, kirundiMsg, karamojongMsg, pokotMsg, nuerAndDinkaMsg, createdAt, updatedAt, updatedBy );

        return eventFullDto;
    }

    @Override
    public Event dtoToEntity(EventFullDto eventFullDto) {
        if ( eventFullDto == null ) {
            return null;
        }

        Event event = new Event();

        event.setId( eventFullDto.id() );
        event.setTitle( eventFullDto.title() );
        event.setStatus( eventFullDto.status() );
        event.setCountry( eventFullDto.country() );
        event.setCounty( eventFullDto.county() );
        event.setPeriodStart( eventFullDto.periodStart() );
        event.setPeriodEnd( eventFullDto.periodEnd() );
        event.setEnglishMsg( eventFullDto.englishMsg() );
        event.setSwahiliMsg( eventFullDto.swahiliMsg() );
        event.setAmharicMsg( eventFullDto.amharicMsg() );
        event.setArabicMsg( eventFullDto.arabicMsg() );
        event.setSomaliMsg( eventFullDto.somaliMsg() );
        event.setFrenchMsg( eventFullDto.frenchMsg() );
        event.setOromoMsg( eventFullDto.oromoMsg() );
        event.setTigrinyaMsg( eventFullDto.tigrinyaMsg() );
        event.setLugandaMsg( eventFullDto.lugandaMsg() );
        event.setKinyarwandaMsg( eventFullDto.kinyarwandaMsg() );
        event.setKirundiMsg( eventFullDto.kirundiMsg() );
        event.setKaramojongMsg( eventFullDto.karamojongMsg() );
        event.setPokotMsg( eventFullDto.pokotMsg() );
        event.setNuerAndDinkaMsg( eventFullDto.nuerAndDinkaMsg() );
        event.setCreatedAt( eventFullDto.createdAt() );
        event.setUpdatedAt( eventFullDto.updatedAt() );
        event.setUpdatedBy( eventFullDto.updatedBy() );

        return event;
    }
}
