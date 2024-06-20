package edu.semester3.eksamen.backend.project.service;

import edu.semester3.eksamen.backend.project.dto.ParticipantDTO;
import edu.semester3.eksamen.backend.project.model.Participant;
import edu.semester3.eksamen.backend.project.repository.ParticipantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {
    ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public ResponseEntity createParticipant(ParticipantDTO participantDTO) {
        Participant newParticipant = new Participant(participantDTO.name(), participantDTO.;
        participantRepository.save(newParticipant);
        return new ResponseEntity("Participant created", null, 201);
    }
    public ParticipantDTO addParticipant(Participant newParticipant) {
        participantRepository.save(newParticipant);
        return new ParticipantDTO(newParticipant.getId(), newParticipant.getName(), newParticipant.getClub(), newParticipant.getGender());
    }

    public ResponseEntity deleteParticipant(int id) {
        participantRepository.deleteById(id);
        return new ResponseEntity("Participant deleted", null, 200);
    }

    public ParticipantDTO updateParticipant(Participant participant) {
        participantRepository.save(participant);
        return participantRepository.findById(Math.toIntExact(participant.getId())).map(b -> new ParticipantDTO(b.getId(), b.getName(), b.getClub(), b.getGender())).orElse(null);
    }

    public Participant getById(int id) {
        return participantRepository.findById(id).orElse(null);
    }

    public Participant getByName(String name) {
        return participantRepository.findByName(name);
    }

    public List<ParticipantDTO> getAllParticipants() {
        return participantRepository.findAll().stream().map(b -> new ParticipantDTO(b.getId(), b.getName(), b.getDescription(), b.getPrice(), b.getIngredients(), b.getImage())).toList();
    }
}
