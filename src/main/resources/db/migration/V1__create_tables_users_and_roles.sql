--Criação da tabela de Perfis/Roles
CREATE TABLE IF NOT EXISTS tb_roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Criação da tabela de Usuários
CREATE TABLE IF NOT EXISTS tb_users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
-- Tabela de junção entre Usuários e Roles, para conectar
CREATE TABLE IF NOT EXISTS tb_users_roles (
    user_id UUID NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users_roles_user FOREIGN KEY (user_id) REFERENCES tb_users (id) ON DELETE CASCADE,
    CONSTRAINT fk_users_roles_role FOREIGN KEY (role_id) REFERENCES tb_roles (id) ON DELETE CASCADE
);

INSERT INTO tb_roles (name) VALUES ('ROLE_MANAGER');
INSERT INTO tb_roles (name) VALUES ('ROLE_SELLER');