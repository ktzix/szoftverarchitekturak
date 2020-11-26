using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace FastFashion.Models
{
    public class FashionItem
    {

        public int Id { get; set; }

        [Required]
        public string Type { get; set; }

        public string Detail { get; set; }

        public string Style { get; set; }

        public string Date { get; set; }

        public string pictureUri { get; set; }

        public int UserId { get; set; }


    }
}
