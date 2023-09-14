package com.avanade.rpg.exceptions.handler;

import com.avanade.rpg.exceptions.*;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(CharacterNotFoundException.class)
    public ResponseEntity<StandardError> characterNotFound(CharacterNotFoundException e,
                                                           HttpServletRequest request) {
        String error = "Character not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(GameNotStartedException.class)
    public ResponseEntity<StandardError> gameNotStarted(GameNotStartedException e,
                                                           HttpServletRequest request) {
        String error = "Game not started yet";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DefenseNotAllowedException.class)
    public ResponseEntity<StandardError> defenseNotAllowed(DefenseNotAllowedException e,
                                                        HttpServletRequest request) {
        String error = "First you have to attack, after that, you'll be able to defend yourself.";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(OpponentHasToBeAMonsterException.class)
    public ResponseEntity<StandardError> opponentHasToBeAMonster(OpponentHasToBeAMonsterException e,
                                                           HttpServletRequest request) {
        String error = "The opponent has to be a monster";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NoSuchTypeException.class)
    public ResponseEntity<StandardError> noSuchType(NoSuchTypeException e,
                                                                 HttpServletRequest request) {
        String error = "There is no character with the described type.";
            HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NoSuchClassException.class)
    public ResponseEntity<StandardError> noSuchClass(NoSuchClassException e,
                                                    HttpServletRequest request) {
        String error = "There is no character with the described class.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(OnlyNameCanBeUpdatedException.class)
    public ResponseEntity<StandardError> onlyNameCanBeUpdated(OnlyNameCanBeUpdatedException e,
                                                     HttpServletRequest request) {
        String error = "You can only edit the name of the character.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NotYourTurnToDefenseException.class)
    public ResponseEntity<StandardError> notYourTurnToDefense(NotYourTurnToDefenseException e,
                                                              HttpServletRequest request) {
        String error = "Oops! It seems like you're trying to defense, but it's not your turn.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NotYourTurnToAttackException.class)
    public ResponseEntity<StandardError> notYourTurnToDefense(NotYourTurnToAttackException e,
                                                              HttpServletRequest request) {
        String error = "Oops! It seems like you're trying to attack, but it's not your turn.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DamageNotAllowedException.class)
    public ResponseEntity<StandardError> damageNotAllowed(DamageNotAllowedException e,
                                                              HttpServletRequest request) {
        String error = "Damage not allowed!";
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(TurnNotStartedException.class)
    public ResponseEntity<StandardError> turnNotStarted(TurnNotStartedException e,
                                                              HttpServletRequest request) {
        String error = "Oops! It seems like the turn doesn't started yet.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(YouCannotPlayAgainstYourselfException.class)
    public ResponseEntity<StandardError> youCannotPlayAgainstYourself(YouCannotPlayAgainstYourselfException e,
                                                        HttpServletRequest request) {
        String error = "Oops! It seems like you are trying to play against yourself.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HistoryDoesntExistsException.class)
    public ResponseEntity<StandardError> historyDoesntExists(HistoryDoesntExistsException e,
                                                                      HttpServletRequest request) {
        String error = "This history doesnt exists.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


}
