const mongoose = require('mongoose');
const yup = require('yup');

const managerSchema = new mongoose.Schema({
  id: String,
  nom: String,
  prenom: String,
  equipe: {
    type: String,
    default: null,
  },
  nb_match_carriere: Number,
  nb_victoire: Number,
  nb_titre: Number,
  historique: [String],
});

const Manager = mongoose.model('Manager', managerSchema);

const managerValidationSchema = yup.object({
  body: yup.object({
    nom: yup.string().required(),
    prenom: yup.string().required(),
    equipe: yup.string().nullable(),
    nb_match_carriere: yup.number().integer().min(0).required(),
    nb_victoire: yup.number().integer().min(0).required(),
    nb_titre: yup.number().integer().min(0).required(),
    historique: yup.array().of(yup.string()).required(),
  }),
});

module.exports = {
  Manager,
  managerValidationSchema,
};
