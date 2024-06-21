package edu.semester3.eksamen.backend.project.dto;

import edu.semester3.eksamen.backend.project.model.Discipline;

public record DisciplineDTO(Integer id, String name, String description) {

        public DisciplineDTO(Integer id, String name) {
            this(id, name, null);
        }

        public DisciplineDTO(String name, String description) {
            this(null, name, description);
        }
        public DisciplineDTO(Discipline discipline) {
            this(discipline.getId(), discipline.getName(), discipline.getDescription());
        }
}
