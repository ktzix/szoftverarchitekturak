using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
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

        public int Age { get; set; }

        public string pictureUri { get; set; }

    }
}
