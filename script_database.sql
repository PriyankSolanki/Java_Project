CREATE TABLE annee_academique (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  annee VARCHAR(255),
                                  active BOOLEAN
);

CREATE TABLE entreprise (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            raison_sociale VARCHAR(255),
                            adresse VARCHAR(255),
                            infos_acces VARCHAR(255)
);

CREATE TABLE maitre_apprentissage (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      prenom VARCHAR(255),
                                      nom VARCHAR(255),
                                      poste VARCHAR(255),
                                      email VARCHAR(255),
                                      telephone VARCHAR(255)
);

CREATE TABLE tuteur_enseignant (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   prenom VARCHAR(255),
                                   nom VARCHAR(255),
                                   email VARCHAR(255),
                                   login VARCHAR(255),
                                   mot_de_passe VARCHAR(255)
);

CREATE TABLE evaluation_ecole (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  theme VARCHAR(255),
                                  note_finale DOUBLE,
                                  commentaires TEXT
);

CREATE TABLE soutenance (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            date DATE,
                            note_finale DOUBLE,
                            commentaires TEXT
);

CREATE TABLE apprenti (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          prenom VARCHAR(255),
                          nom VARCHAR(255),
                          email VARCHAR(255),
                          telephone VARCHAR(255),
                          adresse VARCHAR(255),
                          annee_level VARCHAR(255),
                          etat VARCHAR(255),

                          entreprise_id BIGINT,
                          maitre_apprentissage_id BIGINT,
                          annee_academique_id BIGINT,
                          tuteur_enseignant_id BIGINT,
                          evaluation_ecole_id BIGINT,
                          soutenance_id BIGINT,

                          mission_mots_cles VARCHAR(255),
                          mission_metier_cible VARCHAR(255),
                          mission_commentaires VARCHAR(255),

                          remarques TEXT,
                          feedback_tuteur_enseignant TEXT,

                          CONSTRAINT fk_apprenti_entreprise FOREIGN KEY (entreprise_id)
                              REFERENCES entreprise(id)
                              ON DELETE SET NULL
                              ON UPDATE CASCADE,

                          CONSTRAINT fk_apprenti_maitre FOREIGN KEY (maitre_apprentissage_id)
                              REFERENCES maitre_apprentissage(id)
                              ON DELETE SET NULL
                              ON UPDATE CASCADE,

                          CONSTRAINT fk_apprenti_annee FOREIGN KEY (annee_academique_id)
                              REFERENCES annee_academique(id)
                              ON DELETE SET NULL
                              ON UPDATE CASCADE,

                          CONSTRAINT fk_apprenti_tuteur FOREIGN KEY (tuteur_enseignant_id)
                              REFERENCES tuteur_enseignant(id)
                              ON DELETE SET NULL
                              ON UPDATE CASCADE
);

ALTER TABLE evaluation_ecole
    ADD COLUMN apprenti_id BIGINT UNIQUE,
ADD CONSTRAINT fk_eval_apprenti FOREIGN KEY (apprenti_id)
REFERENCES apprenti(id)
ON DELETE SET NULL
ON UPDATE CASCADE;

ALTER TABLE soutenance
    ADD COLUMN apprenti_id BIGINT UNIQUE,
ADD CONSTRAINT fk_soutenance_apprenti FOREIGN KEY (apprenti_id)
REFERENCES apprenti(id)
ON DELETE SET NULL
ON UPDATE CASCADE;

CREATE TABLE visite (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        date DATE,
                        format VARCHAR(255),
                        commentaires TEXT,
                        apprenti_id BIGINT,
                        CONSTRAINT fk_visite_apprenti FOREIGN KEY (apprenti_id)
                            REFERENCES apprenti(id)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE
);
