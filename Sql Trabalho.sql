create database memoria;
use memoria;
-- Tabela de Jogadores
CREATE TABLE jogador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL -- Use hashing para senhas!
);

-- Tabela de Duplas
CREATE TABLE dupla (
    id_dupla INT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL,
    pontos INTEGER NOT NULL,
    sequencia VARCHAR(255) NOT NULL,
    nivel INTEGER NOT NULL
);

-- Tabela de Jogos
CREATE TABLE jogo (
    id_jogo INT AUTO_INCREMENT PRIMARY KEY,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    pontos INTEGER NOT NULL,
    sequencia VARCHAR(255) NOT NULL, -- Armazena a sequência jogada, ex: '1234'
    nivel INTEGER NOT NULL, -- Representa o nível alcançado no jogo, 0: fácil, 1: médio, 2, dificl
    id_player1 INT NOT NULL,
    id_dupla INT,
    FOREIGN KEY (id_player1) REFERENCES jogador(id),
    FOREIGN KEY (id_dupla) REFERENCES dupla(id_dupla)
);

-- Tabela de Conquistas
CREATE TABLE conquista (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT, -- Descrição da conquista
    pontuacao_necessaria INTEGER NOT NULL
);

-- Relacionamento entre Jogadores e Conquistas
CREATE TABLE jogador_conquista (
    jogador_id INT,
    conquista_id INT,
    PRIMARY KEY (jogador_id, conquista_id),
    FOREIGN KEY (jogador_id) REFERENCES jogador(id) ON DELETE CASCADE,
    FOREIGN KEY (conquista_id) REFERENCES conquista(id) ON DELETE CASCADE
);


INSERT INTO jogador (nickname, email, senha) VALUES
('PlayerOne', 'playerone@example.com', 'hashedpassword1'),
('PlayerTwo', 'playertwo@example.com', 'hashedpassword2'),
('PlayerThree', 'playerthree@example.com', 'hashedpassword3');

INSERT INTO dupla (nickname, pontos, sequencia, nivel) VALUES
('TeamA', 150, 'RGBY', 3),
('TeamB', 200, 'YGBR', 4),
('TeamC', 100, 'BRGY', 2);

-- Inserindo jogos para jogadores individuais
INSERT INTO jogo (pontos, sequencia, nivel, id_player1) VALUES
(180, 'RGBY', 3, 1), -- Jogo do PlayerOne
(210, 'YGBR', 4, 2), -- Jogo do PlayerTwo
(120, 'BRGY', 2, 3); -- Jogo do PlayerThree

-- Inserindo jogos para duplas
INSERT INTO jogo (pontos, sequencia, nivel, id_player1, id_dupla) VALUES
(220, 'RGBY', 5, 1, 1), -- PlayerOne jogando na TeamA
(250, 'YGBR', 6, 2, 2), -- PlayerTwo jogando na TeamB
(140, 'BRGY', 3, 3, 3); -- PlayerThree jogando na TeamC

select id from jogador;

SELECT max(pontos) as pontos from jogo where id_player1 = 1;

