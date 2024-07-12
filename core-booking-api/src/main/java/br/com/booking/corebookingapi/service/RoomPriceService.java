package br.com.booking.corebookingapi.service;

import br.com.booking.corebookingapi.dto.CreateUpdateRoomPriceDTO;
import br.com.booking.corebookingapi.dto.RoomPriceDTO;
import br.com.booking.corebookingapi.entity.RoomPrice;
import br.com.booking.corebookingapi.exception.custom.RecordNotFoundException;
import br.com.booking.corebookingapi.mapper.RoomPriceMapper;
import br.com.booking.corebookingapi.repository.RoomPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomPriceService {

    @Autowired
    private RoomPriceRepository roomPriceRepository;
    @Autowired
    private RoomPriceMapper roomPriceMapper;
    @Autowired
    private MessageSource messageSource;

    public Page<RoomPriceDTO> findAll(Pageable pageable) {
        Page<RoomPrice> pageRoomPrices = roomPriceRepository.findAll(pageable);

        if (pageRoomPrices.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return pageRoomPrices.map(roomPriceMapper::toDto);
    }

    public RoomPriceDTO findRoomPriceById(Long id) {
        Optional<RoomPrice> roomPrice = roomPriceRepository.findById(id);

        if (!roomPrice.isPresent()) {
            throw new RecordNotFoundException();
        }

        return roomPriceMapper.toDto(roomPrice.get());
    }

    public RoomPriceDTO createRoomPrice(CreateUpdateRoomPriceDTO createUpdateRoomPriceDTO) {
        RoomPrice roomPrice = roomPriceMapper.toEntity(createUpdateRoomPriceDTO);
        return roomPriceMapper.toDto(roomPriceRepository.save(roomPrice));
    }

    public RoomPriceDTO updateRoomPrice(Long id, CreateUpdateRoomPriceDTO createUpdateRoomPriceDTO) {
        Optional<RoomPrice> oldRoomPrice = roomPriceRepository.findById(id);

        if (!oldRoomPrice.isPresent()) {
            throw new RecordNotFoundException();
        }

        RoomPrice roomPrice = oldRoomPrice.get();
        roomPriceMapper.updateRoomPriceFromDto(createUpdateRoomPriceDTO, roomPrice);

        return roomPriceMapper.toDto(roomPriceRepository.save(roomPrice));
    }


}
